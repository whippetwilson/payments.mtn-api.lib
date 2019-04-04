package com.plydot.mtnmomoapi.products;

import com.plydot.mtnmomoapi.model.TransferGetResponse;
import com.plydot.mtnmomoapi.utils.PayeIDType;

import java.util.UUID;

public interface IDisbursements extends ICollections {

    /**
     * Transfer out of account
     * @return transfer status
     */
    public TransferGetResponse transfer(String amount, String currency, String account,
                                        String message, PayeIDType payeIDType);

    public TransferGetResponse transfer(String amount, String currency, String account,
                                        String message, PayeIDType payeIDType, UUID externalId);

    public TransferGetResponse transfer(String amount, String currency, String account,
                                        String message, PayeIDType payeIDType, UUID externalId,
                                        String XreferenceId);

    public TransferGetResponse transfer(String amount, String currency, String account,
                                        String message, PayeIDType payeIDType, String XreferenceId);

    /**
     * Check for transfer status
     * @param XReferenceId transaction reference
     * @return transfer status
     */
    public TransferGetResponse getTransferStatus(String XReferenceId);
}
